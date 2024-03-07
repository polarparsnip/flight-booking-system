import os

path="flightBookingSystem.db"

# execute SQLite commands
if os.name == 'nt':
    cmd = f'cmd /c "sqlite3 db/{path} < db/schema.sql && sqlite3 db/{path} < db/insert.sql"'

else:
    cmd = f"cd db && sqlite3 {path} < schema.sql && sqlite3 {path} < insert.sql"

os.system(cmd)
