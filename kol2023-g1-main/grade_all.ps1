
[System.Environment]::SetEnvironmentVariable('WP_USER','fweadmin')
[System.Environment]::SetEnvironmentVariable('WP_PASS','n0mis11$')



$students = [System.Collections.ArrayList]@(
  "193104",  "203195",  "191099",  "193169",  "196011",  "191034",  "163032",  "206055",  "183073",  "201111",  "191026",  "203011",  "201042",  "201030",  "202029",  "196091",  "183052",  "181182",  "183018",  "193007",  "201135",  "181001",  "196106",  "191023",  "203188",  "183089",  "203020",  "171254",  "203037",  "191049",  "185025",  "201033",  "206017",  "191084",  "riste",  "201008",  "203181",  "191300",  "181065",  "203147",  "181129",  "201131",  "201102",  "203005",  "203084",  "201110",  "203024",  "201003",  "183238",  "195018",  "183046",  "151159",  "201157",  "193102",  "191553",  "203068",  "186120",  "201140",  "201125",  "191125",  "193057",  "201531",  "201046",  "193179",  "193192",  "203019",  "201130",  "181274",  "195012",  "191503",  "201149",  "201050",  "191170",  "202023",  "202001",  "195078",  "191162",  "203095",  "196069",  "191251",  "201506",  "203012",  "203141",  "201036",  "201147",  "201526"
)

foreach ($s in $students ) {
    git restore  -s@ -SW -- src/main/
    git clean -fdx src/main/
    .\mvnw.cmd test-compile exec:java "-Dexec.classpathScope=test" "-Dexec.mainClass=LoadSolution" "-Dexec.args=$s"
    [System.Environment]::SetEnvironmentVariable('student_index',$s)
    .\mvnw.cmd test
}