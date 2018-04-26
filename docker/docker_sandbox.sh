#!/bin/bash
docker run -i -v `pwd`:/sandbox --rm sandbox $@
