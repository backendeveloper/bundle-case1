resource "docker_image" "consul" {
  name         = "hashicorp/consul:latest"
  keep_locally = false
}

resource "docker_network" "consul_network" {
  name = "consul_network"
}

resource "docker_container" "consul" {
  name  = "consul-server"
  image = docker_image.consul.image_id
  ports {
    internal = 8500
    external = 8500
  }
  command = [
    "agent",
    "-server",
    "-bootstrap",
    "-client=0.0.0.0",
    "-ui",
  ]
}

resource "consul_keys" "triplet_filter_config" {
  datacenter = "dc1"

  key {
    path  = "service/data-processor"
    value = jsonencode({
      "RulesConstants": {
        "SumThresholdCount": 90,
        "GroupSizeCount": 3
      }
    })
  }

  depends_on = [docker_container.consul]
}