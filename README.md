# Azure-Databricks-JAR-DevOps
Compile and deploy a custom JAR file to Databricks using Azure DevOps


```
mvn archetype:generate \
  -DgroupId=org.linecount \
  -DartifactId=linecount \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

cd linecount

mvn package

dbfs mkdirs dbfs:/linecount

To run
Classname: org.linecount.LineCount
Parameters: false dbfs:/databricks-datasets/README.md dbfs:/linecount
```