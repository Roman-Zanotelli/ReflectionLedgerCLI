#!/bin/bash

#EXIT IMMIDATELY ON FAIL (INPORTANT)
set -e

#Set Up Build Vars
SRC_DIR="src"
COMP_DIR="comp"
BUILD_DIR="build"
JAR_NAME="ledger.jar"
MAIN_PKG="com.pluralsight."
MAIN_CLASS="App"

#Create Build Dir (if it doesnt exist)
mkdir -p "$COMP_DIR"
mkdir -p "$BUILD_DIR"

#Tell javac to compile all .java files inside SRC_DIR to OUT_DIR
javac -d "$COMP_DIR" $(find "$SRC_DIR" -name "*.java")

#Pop manifest file
echo "Main-Class: $MAIN_PKG$MAIN_CLASS" > "$COMP_DIR/manifest.txt"

#Build final jar into BUILD_DIR from compiled classes/manifest inside COMP_DIR
jar cfm "$BUILD_DIR/$JAR_NAME" "$COMP_DIR/manifest.txt" -C "$COMP_DIR" .

#Echo Out the build path & jar
echo "$BUILD_DIR/$JAR_NAME"