#!/bin/bash
docker run -v `pwd`:/sandbox --rm sandbox $@
