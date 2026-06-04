#!/bin/bash

CUR=$(pwd)
TMPDIR="$(dirname -- "${0}")"

SC_RELEASE_TAG="v$SC_VERSION"
SOURCE_DIR="$CUR/modules/swagger-annotations/target/javadocprep/swagger-core/${SC_VERSION}/apidocs"

if [ ! -d "$SOURCE_DIR" ]; then
  echo "Missing generated javadocs at $SOURCE_DIR"
  find "$CUR/modules/swagger-annotations/target" -maxdepth 3 \( -type d -o -type f \) | sort
  exit 1
fi

cp -aR "$SOURCE_DIR" "$TMPDIR"
cp -a "$CUR/CI/publish-javadocs.sh" "$TMPDIR/publish-javadocs.sh"

