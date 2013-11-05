## Forklift

Forklift is a small library for Android apps that provides access to SQLite databases that are shipped as part of the application package – either as asset or raw resource. It's thread-safe, easy to use and supports Android 2.3 and up.

### Usage

1. Put the `forklift-*.jar` on your classpath or declare it as dependency in your pom.xml, build.gradle or ...

        <dependency>
            <groupId>ch.gluecode</groupId>
            <artifactId>forklift</artifactId>
            <version><!-- latest version --></version>
        </dependency>

2. Prepare the SQLite database on your computer and place it either in the `assets` directory of your Android project or in `res/raw`.
3. Extend `AbstractResourceSqliteOpenHelper` (if the database is in `res/raw`) or `AbstractAssetSqliteOpenHelper` (if the databse is in the `assets` directory):
    
        public class ExampleResourceSqliteOpenHelper extends AbstractResourceSqliteOpenHelper {
        
            private static final int RAW_RESOURCE = R.raw.exampledb;
        
            private static final String DATABASE_NAME = "example.db";
        
            private static final int DATABASE_VERSION = 1;
        
            public MountainsResourceSqliteOpenHelper(Context context) {
                super(context, RAW_RESOURCE, DATABASE_NAME, DATABASE_VERSION);
            }
        
            @Override
            protected void onConfigure(SQLiteDatabase db) {
                // Enable foreign key support or the like
            }
        
            @Override
            protected void onPostCopy(SQLiteDatabase db, int oldVersion, int newVersion) {
                // Change the database's contents if required
            }
        }
    
4. Use `ExampleResourceSqliteOpenHelper` like you would use a normal `SQLiteOpenHelper` to access the database.

For a full working example, see the sample application.

### How it works

The first time `AbstractBundledSqliteOpenHelper#getDatabase()` is called, Forklift automatically copies the bundled database to the application's data directory (`/data/data/<app-pkg>/databases/`) so it can be accessed using standard Android APIs. On subsequent calls to `AbstractBundledSqliteOpenHelper#getDatabase()`, the database copied over to the application's data directory will be reused which means that any changes applied after the first copy will be retained. To trigger an update, increase the version number of the database and Forklift will automatically copy the bundled database to the application's data directory again. 
