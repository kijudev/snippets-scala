# snippets-scala

Scala 3 + ZIO learning monorepo.

```
modules/<name>/     sbt subprojects (one per project/topic), auto-aggregated
snippets/*.scala    single-file scala-cli experiments
project/            sbt config + shared dependency versions (Dependencies.scala)
```

## Usage

```
just                       # list commands
just new my-topic          # scaffold modules/my-topic and register it
just run hello-zio
just test http-api
just watch http-api        # restart on save
just snippet snippets/hello.scala
just repl
```
