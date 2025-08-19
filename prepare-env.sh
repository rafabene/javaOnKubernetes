#!/bin/bash

podman machine stop
podman machine set --rootful
podman machine start