CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(255)
);

CREATE TABLE artworks (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          description VARCHAR(255),
                          img_url VARCHAR(255),
                          price DECIMAL(10,2),
                          stock INT NOT NULL,
                          categoria_id BIGINT,
                          CONSTRAINT fk_artworks_category
                              FOREIGN KEY (categoria_id)
                                  REFERENCES categories (id)
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       address VARCHAR(255),
                       description VARCHAR(255),
                       image_profile_url VARCHAR(255),
                       n_account VARCHAR(255)
);