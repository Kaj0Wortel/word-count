# Word counter

## Getting started
This implementation has been tested with [GlassFish 6.2.3](https://www.eclipse.org/downloads/download.php?file=/ee4j/glassfish/glassfish-6.2.3.zip).
Use `./gradlew :export` to generate the War, JavaDoc, and zipped sources.
To deploy the application using an unmodified GlassFish 6 install, you can run the following:
```bash
# Build the project.
${PROJECT_DIR}/gradlew :war
# Start glassfish.
${GLASSFISH6_DIR}/bin/asadmin start-domain domain1
# Deploy application at 'http://localhost:8080/word-count'.
${GLASSFISH6_DIR}/bin/asadmin deploy --contextroot "word-count" ${PROJECT_DIR}/build/libs/word-count-1.0.war
```
Here, `${PROJECT_DIR}` denotes the root directory of the project, and `${GLASSFISH6_DIR}` the install directory of GlassFish 6.
Run the following to undeploy and cleanup:
```bash
# Undeploy application.
${GLASSFISH6_DIR}/bin/asadmin undeploy word-count-1.0
# Stop glassfish.
${GLASSFISH6_DIR}/bin/asadmin stop-domain domain1
```

## API
- `<domain>/api/analyze/<text>/max-freq`: Returns the frequency of the most occurring word in `<text>` as a plain text integer.<br>
  Example: `<domain>/api/analyze/a|b,c c/max-freq` gives `2`.
- `<domain>/api/analyze/<text>/freq-of?word=<word>`: Returns the number of occurrences of `<word>` in `<text>` as a plain text integer.<br>
  Example: `<domain>/api/analyze/a|b,c c/freq-of?word=c` gives `2`.
- `<domain>/api/analyze/<text>/n-most-freq?n=<n>`: Returns the list of at most `<n>` occurring words and their frequencies in `<text>`. Ties are resolved by alphabetically ordering the words. The response is formatted as a JSON.<br>
  Example: `<domain>/api/analyze/a|b,c c/n-most-freq?n=2` gives `[{"frequency":2,"word":"c"},{"frequency":1,"word":"a"}]`

`<text>` consists of words (i.e. `[a-zA-Z]+`) separated by other characters (i.e. `[^a-zA-Z]`). The operations are performed case insensitive, i.e. `The` and `the` are considered equal.
