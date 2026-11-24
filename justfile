# Scala 3 + ZIO monorepo - run `just` to list commands.

# List commands
default:
    @just --list

# Compile all modules
compile:
    sbt compile

# Test all modules, or a single one: `just test hello-zio`
test module="":
    sbt {{ if module == "" { "test" } else { module + "/test" } }}

# Run a module's main: `just run hello-zio [args]`
run module *args:
    sbt "{{ module }}/run {{ args }}"

# Re-run a module on every file change
watch module:
    sbt "~{{ module }}/run"

# Run a scala-cli snippet: `just snippet snippets/hello.scala`
snippet file *args:
    scala-cli run {{ file }} -- {{ args }}

# Scala REPL with ZIO on the classpath
repl:
    scala-cli repl --dep dev.zio::zio:2.1.27

# Interactive sbt shell (fastest for repeated commands)
sbt:
    sbt

# Format all code
fmt:
    scalafmt

# Check formatting (CI)
fmt-check:
    scalafmt --check

# Create a new module: `just new my-project`
[unix]
new name:
    #!/usr/bin/env bash
    set -euo pipefail
    dir="modules/{{ name }}"
    [ -e "$dir" ] && { echo "$dir already exists"; exit 1; }
    mkdir -p "$dir/src/main/scala" "$dir/src/test/scala"
    printf '%s\n' \
      'import zio.*' '' \
      'object Main extends ZIOAppDefault:' \
      '  def run = Console.printLine("Hello from {{ name }}!")' \
      > "$dir/src/main/scala/Main.scala"
    printf '%s\n' \
      'import zio.test.*' '' \
      'object MainSpec extends ZIOSpecDefault:' \
      '  def spec = suite("{{ name }}")(' \
      '    test("works")(assertTrue(1 + 1 == 2))' \
      '  )' \
      > "$dir/src/test/scala/MainSpec.scala"
    printf '\nlazy val `%s` = module("%s")\n' "{{ name }}" "{{ name }}" >> build.sbt
    echo "Created $dir and registered it in build.sbt. Try: just run {{ name }}"

# Delete build outputs
clean:
    sbt clean
    rm -rf .scala-build snippets/.scala-build
