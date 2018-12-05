DROP TABLE IF EXISTS "employees" CASCADE;
DROP TABLE IF EXISTS "departments" CASCADE;
DROP TABLE IF EXISTS "organizations" CASCADE;

DROP TABLE IF EXISTS "organizations" CASCADE;
CREATE TABLE IF NOT EXISTS "organizations" (
  "id"   SERIAL ,
  "name" VARCHAR(255) NOT NULL UNIQUE,
  "created_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  "updated_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "departments" CASCADE;
CREATE TABLE IF NOT EXISTS "departments" (
  "id"   SERIAL ,
  "name" VARCHAR(255),
  "created_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  "updated_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  "organization_id" INTEGER REFERENCES "organizations" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "employees" CASCADE;
CREATE TABLE IF NOT EXISTS "employees" (
  "id"   SERIAL ,
  "first_name" VARCHAR(255) NOT NULL,
  "last_name" VARCHAR(255) NOT NULL,
  "title" VARCHAR(255) NOT NULL,
  "role" VARCHAR(255),
  "created_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  "updated_at" TIMESTAMP WITH TIME ZONE NOT NULL,
  "department_id" INTEGER REFERENCES "departments" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY ("id")
);
