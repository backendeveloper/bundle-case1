terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }

    consul = {
      source  = "hashicorp/consul"
      version = "~> 2.0"
    }
  }
}

provider "docker" {
  host = "unix:///var/run/docker.sock"
}

# provider "consul" {
#   address = "localhost:8500"
# }

provider "consul" {
  address = "http://localhost:8500"
}