# Gringo Card Game (Java)

## Overview

A small Java-based multiplayer card game server and client for a simplified Gringo-like card game. The codebase includes:

- `Card.java`: card representation (rank, suit, value).
- `Deck.java`: deck creation, shuffling, deck listing.
- `Game.java`: game state initialization and main entry for local game debug.
- `GameServer.java`: server socket, player handler list, game lifecycle control.
- `PlayerHandler.java`: per-player server-side connection handler, I/O and messaging.
- `Player.java`: client-side game participant, send/receive loops.
- `Rules.java`: placeholder for future rules implementation.

## What it does

NOTE: Current Game capabilities aren't doable nor runnable

- Starts a server listening on port `1234`.
- Accepts player connections and captures player username.
- Keeps a list of connected players, echoes/handles player commands.
- Tracks game phase at a high level (`SETUP`, `PLAYING`, `ENDING`, `FINISHED`).

## How to run (local test)

1. Open terminal #1.

2. Start server:

```bash
cd /Users/nuno.alves/Desktop/gringo-card-game
javac GameServer.java
java GameServer
```

3. Open terminal #2 (player #1):

```bash
cd /Users/nuno.alves/Desktop/gringo-card-game
javac Player.java
java Player
```

4. Open terminal #3 (player #2):

```bash
cd /Users/nuno.alves/Desktop/gringo-card-game
javac Player.java
java Player
```

5. Follow prompts to enter username and start sending commands.

## Notes

- `GameServer` uses `PlayerHandler` threads for each connection.
- `Player` uses `Scanner` to read user input and sends it to the server.
- If you stop a player, the handler will disconnect and remove them.

## Improvements to do

- Implement full Gringo game logic in `Game`, `GameServer`, `PlayerHandler`.
- Add clean shutdown signals for server and clients.
- Use `try-with-resources` for all `Socket` and I/O resources.
- Implement `Rules` and use it to validate moves.

