DROP TABLE IF EXISTS track_artist;

DROP TABLE IF EXISTS release_track;

DROP TABLE IF EXISTS release_artist;

DROP TABLE IF EXISTS release_genre;

DROP TABLE IF EXISTS artist;

DROP TABLE IF EXISTS track;

DROP TABLE IF EXISTS release;

DROP TABLE IF EXISTS genre;

DROP TYPE IF EXISTS release_type;

CREATE TYPE release_type AS ENUM ('Album', 'Single', 'EP');

CREATE TABLE IF NOT EXISTS artist (
    artist_id INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
    name      VARCHAR(127) NOT NULL,

    UNIQUE      (name),
    PRIMARY KEY (artist_id)
);

CREATE TABLE IF NOT EXISTS track (
    track_id    INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
    title       VARCHAR(255) NOT NULL,
    duration_ms INT          NOT NULL,

    PRIMARY KEY (track_id)
);

CREATE TABLE IF NOT EXISTS release (
    release_id   INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
    title        VARCHAR(255) NOT NULL,
    type         release_type NOT NULL,
    year         INT          NOT NULL,

    PRIMARY KEY (release_id)
);

CREATE TABLE IF NOT EXISTS genre (
    genre_id INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR(63) NOT NULL,

    UNIQUE      (name),
    PRIMARY KEY (genre_id)
);

CREATE TABLE IF NOT EXISTS track_artist (
    track_artist_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    track_id        INT NOT NULL,
    artist_id       INT NOT NULL,

    UNIQUE      (track_id, artist_id),
    PRIMARY KEY (track_artist_id),
    FOREIGN KEY (track_id)  REFERENCES track (track_id)   ON DELETE CASCADE,
    FOREIGN KEY (artist_id) REFERENCES artist (artist_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS release_artist (
    release_artist_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    release_id        INT NOT NULL,
    artist_id         INT NOT NULL,

    UNIQUE      (release_id, artist_id),
    PRIMARY KEY (release_artist_id),
    FOREIGN KEY (release_id) REFERENCES release (release_id) ON DELETE CASCADE,
    FOREIGN KEY (artist_id)  REFERENCES artist (artist_id)   ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS release_track (
    release_track_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    release_id       INT NOT NULL,
    track_id         INT NOT NULL,
    track_number     INT NOT NULL,

    UNIQUE      (release_id, track_id),
    PRIMARY KEY (release_track_id),
    FOREIGN KEY (release_id) REFERENCES release (release_id) ON DELETE CASCADE,
    FOREIGN KEY (track_id)   REFERENCES track (track_id)     ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS release_genre (
    release_genre_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    release_id       INT NOT NULL,
    genre_id         INT NOT NULL,

    UNIQUE      (release_id, genre_id),
    PRIMARY KEY (release_genre_id),
    FOREIGN KEY (release_id) REFERENCES release (release_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id)   REFERENCES genre (genre_id)     ON DELETE CASCADE
);
