# Dummy File Generator

This tool will allow you to generate dummy files with given file sizes. These files are binary, hence cannot be read via text editors.

## Build

To build the tool, run the following command.

```bash
mvn clean install
```

## Usage

```bash
java -jar <PATH_TO_JAR>/file-generator-1.0-SNAPSHOT.jar <FILE_NAME> <FILE_SIZE>
```

Ex.
```bash
java -jar <PATH_TO_JAR>/file-generator-1.0-SNAPSHOT.jar tool.dat <FILE_SIZE>
```