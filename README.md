# CQL Evaluation Engine

The HL7 Clinical Quality Language specification is designed to enable accurate authoring and automated sharing of computable knowledge artifacts including quality measures, decision support rules, orders sets and documentation templates. The specification is constructed using a layered approach informed by modern programming language and compiler design which facilitates the development of language processing applications including static analysis, translation, and evaluation.

The specification includes informative open source tooling that can be used to verify the semantics of CQL libraries; to produce the sharable format, Expression Logical Model (ELM), of those libraries; and to evaluate the resulting libraries using a prototypical JavaScript-based ELM interpreter.

This project builds on that tooling to provide an open source Java-based evaluation engine capable of evaluating the result of any CQL expression.

# License

Copyright 2016 University of Utah

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

# How to Use

The following commands will build the CQL Evaluation Engine and then
execute a CQL/ELM file or the CQL engine validation suite against it.
The syntax is specific to Unix-like operating systems.
You need to "cd" into the main directory of the project first.
You need to install Java and Gradle before running the commands.

This is all you need to do to simply build the CQL Evaluation Engine:

```
gradle fatJar
```

After the Engine is built, this is the most direct way to execute any CQL
or ELM file:

```
java -jar ./cql-engine/build/libs/cql-engine-all-1.2.38-SNAPSHOT.jar filename
```

A simple wrapper script exists for the above so you can say this instead:

```
./cql-runner filename
```

However you have to mark that wrapper script as executable first (once),
such as with this command:

```
chmod 755 ./cql-runner
```

## Docker Development

Use the Makefile and Dockerfile to create a docker based intellij development environment for usage on Linux (not currently tested on Mac or Windows). The following command will create the docker image.

```
make build
```

Once you have the image, you can run the IDE by using the following command.

```
make run
```
