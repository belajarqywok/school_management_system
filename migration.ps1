function Set-Defaults {
    if (-not $env:HOST) { $env:HOST = "127.0.0.1" }
    if (-not $env:PORT) { $env:PORT = "3306" }
    if (-not $env:USER) { $env:USER = "root" }
}

function Run-MySQL-Scripts {
    $mysqlSchemas = "mysql.schemas.sql"
    $mysqlSeeders = "mysql.seeders.sql"

    & mysql --host=$env:HOST --port=$env:PORT --user=$env:USER --execute="source $mysqlSchemas"
    & mysql --host=$env:HOST --port=$env:PORT --user=$env:USER --execute="source $mysqlSeeders"
}

function Main {
    Set-Defaults
    Run-MySQL-Scripts
}

Main
