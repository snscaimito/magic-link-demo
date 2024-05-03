# Magic Link Login Demo

Magic Links are a simple way to allow authorized access to a website without building a full-fledged user management system.

**Instead of actually sending an email this demo displays the magic link in the frontend application where it can be clicked.**


## Development

First, start the services as tmux sessions with:

    ./devStart

Then you can edit the source.

There is "local CI" available. Stop the services started by `devStart` and then run

    ./ci

This uses tmux and runs Cypress in headless mode. The usual videos and screenshots will be created in case of any failures.


## How logging in with Magic Link works

Instead of a username and password a magic link that is supposed to be known only by the right person is used. To ensure that only the right person knows the magic link email is used to deliver the magic link. For each login the magic link will be different so that for every login an email has to be sent and received.

Instead of email another delivery method can also be used.

### Signup

1. Register email address
2. Receive email to confirm (console output for demo purposes)

### Login

1. Enter email address
2. If email address is known, receive magic link in email (console output for demo purposes)
3. Use magic link to log in
