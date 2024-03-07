import os

path="flightBookingSystem.db"

# execute SQLite commands
if os.name == 'nt':
    cmd = f'cmd /c "sqlite3 sql/{path} < sql/schema.sql && sqlite3 sql/{path} < sql/insert.sql"'

else:
    cmd = f"cd sql && sqlite3 {path} < schema.sql && sqlite3 {path} < insert.sql"

os.system(cmd)
