{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  buildInputs = with pkgs; [
    sbt
    gnupg
    pinentry-curses
  ];

  shellHook = ''
    export GPG_TTY=$(tty)
  '';
}
