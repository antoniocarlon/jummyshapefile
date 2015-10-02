# jummyShapefile  (っ˘ڡ˘ς)

jummyShapefile is a lightweight, Android compatible, Java library to work with (read for now) ESRI Shapefiles.

### Current version
v0.2.0

### Objectives
 - Create a fast, Android compatible Java library to work with Shapefiles
 - Query the entities in a Shapefiles using a rectangle
 - Query the data of the shapes, not only its geometry using the mandatory .dbf file (dBase IV) included in the Shapefile specification
 - Work with Shapefiles of virtually any size
 - Learn more about the Shapefile format
 - Have fun working with binary files

### Compatibility
Java 1.5 and above

### Easy peasy (Java 7 for brevity)
```
try (
	FileInputStream shpIS = new FileInputStream("/path/to/shapefile.shp");
	FileInputStream shxIS = new FileInputStream("/path/to/shapefile.shx");
	FileInputStream dbfIS = new FileInputStream("/path/to/shapefile.dbf");
	FileInputStream prjIS = new FileInputStream("/path/to/shapefile.prj");
	Shapefile shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS)) {

	// Get the number of entities in the shapefile
	final int numberOfEntities = shapefile.getNumEntities();

	// Get the entities in the shapefile whose MBR (Minimum Bounding Rectangle) intersects a given rectangle.
	// The rectangle must be in the same projection as the Shapefile
	final List<Entity> entities = shapefile.getEntitiesInRectangle(440539.16, 4471192.95, 448629.42, 4476120.37, true);

	//TODO: Do some cool stuff with the geometries
}
```

### Tutorial
```
// Get the projection of the shapefile
String projection = shapefile.getProjection();

// Get the number of entities stored in the shapefile
int numberOfEntities = shapefile.getNumEntities();

// Get the field descriptors of the entity data
List<DBFFieldDescriptor> fieldDescriptors = shapefile.getDataFieldDescriptors();

// Get the entity that matches a given record number
Entity entity = shapefile.getEntityByRecordNumber(1487, true);

// Get the entities whose MBR (Minimum Bounding Rectangle) intersects a given rectangle
List<Entity> entities = shapefile.getEntitiesInRectangle(440539.16, 4471192.95, 448629.42, 4476120.37, true);

// Get the entity data for a given record number
List<DBFField> entityData = shapefile.getEntityDataByRecordNumber(1487);
```

### Limitations
- jummyShapefile takes into account the following files:
  - .shp (mandatory in the Shapefile format) - the feature geometries
  - .shx (mandatory in the Shapefile format) — the positional shape index
  - .dbf (mandatory in the Shapefile format) — the attributes for the shapes (dBase IV)
  - .prj (optional in the Shapefile format) — the projection and coordinate system of the geometries
- jummyShapefile only reads the following shape types defined in the Shapefile format:
  - Null shape
  - Point
  - Polyline
  - Polygon
  - MultiPoint
- jummyShapefile only reads the following field types defined in the DBF format:
  - N (Short Integer and Long Integer in Shapefiles)
  - F (Float and Double in Shapefiles)
  - C (Text in Shapefiles)
  - D (Date in Shapefiles)

### Future work
- Read all the shape types defined in the Shapefile format:
  - PointZ
  - PolylineZ
  - PolygonZ
  - MultiPointZ
  - PointM
  - PolylineM
  - PolygonM
  - MultiPointM
  - MultiPatch
- (Maybe) Read the (not documented by Esri) .sbn file with the spatial indexes for the shapes. Using this spatial index a performance boost of about 20% (estimation) is expected when querying shapes using a rectangle.
- Delete geometries
- Insert geometries
- Update (delete + insert?) geometries

### References
1. http://www.esri.com/library/whitepapers/pdfs/shapefile.pdf
2. http://www.dbase.com/KnowledgeBase/int/db7_file_fmt.htm

### License
Copyright 2015 ANTONIO CARLON

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
