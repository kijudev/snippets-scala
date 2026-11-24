{
  description = "Scala 3 + ZIO monorepo - dev environment";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";

  outputs =
    { nixpkgs, ... }:
    let
      # Linux (x86/ARM) and Apple Silicon macOS.
      # Windows: use WSL2 (it is x86_64-linux). Nix does not run natively on Windows.
      # Intel macOS (x86_64-darwin) was dropped from nixpkgs-unstable in 26.11.
      systems = [
        "x86_64-linux"
        "aarch64-linux"
        "aarch64-darwin"
      ];
      forAllSystems = f: nixpkgs.lib.genAttrs systems (system: f nixpkgs.legacyPackages.${system});
    in
    {
      devShells = forAllSystems (
        pkgs:
        let
          # One JDK for everything, so sbt, scala-cli and metals agree.
          jdk = pkgs.jdk25;
          withJdk = p: p.override { jre = jdk; };
        in
        {
          default = pkgs.mkShell {
            packages = [
              jdk
              (withJdk pkgs.sbt) # build tool (the sbt version comes from project/build.properties)
              (withJdk pkgs.scala-cli) # single-file snippets, REPL
              (withJdk pkgs.metals) # language server (VS Code / Neovim / Helix / Zed)
              (withJdk pkgs.coursier) # `cs` - Scala artifact fetcher/installer
              pkgs.scalafmt # formatter
              pkgs.just # task runner
              pkgs.git
            ];

            JAVA_HOME = jdk.home;

            shellHook = ''
              echo "Scala 3 + ZIO dev shell  |  java $(java -version 2>&1 | head -1 | cut -d'"' -f2)  |  run 'just' to list commands"
            '';
          };
        }
      );

      formatter = forAllSystems (pkgs: pkgs.nixfmt);
    };
}
