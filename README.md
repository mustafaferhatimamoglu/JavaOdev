# JavaOdev
visual programming 2021 


Grup65	
1030522811	Mustafa Ferhat İMAMOĞLU
1030510162	Mustafa KURT
1030520032	Abdullah BOZLAĞAN		

TODO List

1-Sorting arrays and list: Define the natural order of a user-defined type and sort an
array/list.
2-)text ve xml oku
3-) grafiğe bas 
3-A)Plot bar
3-B)Line chart

örnek dosya erudm de bulabilceğeğim belirtilmiş fakat malesef bulunamadı şaşırdıkmı ? (210501)
dosya için format belirtilmiş en azından örn
<record>
    <date>A</date>
    <name>B</name>
    <country>C</country>
    <value>D</value>
    <category>E</category>
</record>

excel soyası hazırlar ve texte ve xmle ordan çevirmek mantıklı geldi
-random date -https://onlinerandomtools.com/generate-random-date
-random name -http://listofrandomnames.com/index.cfm?textarea
-random country -https://commentpicker.com/random-country-generator.php
-random value -https://onlinerandomtools.com/generate-random-numbers
-kategori gruplamaya izin vermesi adına yakıt tiplerine kullanıldı

istenilenler xlsx formatında DATA klasörünün altında
-xml linkteki destekle hazırlandı -https://spreadsheeto.com/xml/
-text dosyası csv formatını kastettiğinden kaydı sağlandı



The main learning objectives of this assignment are:
· Sorting arrays and list: Define the natural order of a user-defined type and sort an
array/list.
· Reading input files: Read text and XML files and parse them.
· Data visualization and graphics: Plot bar and line charts to produce an interesting
visualization.

In your design, you need to create classes that are given in this section. Besides, you can
create some other classes and add new fields and method to given classes.
You must create an abstract Chart class, BarChart and LineChart classes that extends Chart
class. You must create Bar and Line classes whose objects can added to BarChart and
LineChart objects, respectively. Bar and Lines classes must implement the Comparable
interface.
A bar collects related information (name, value, and category) for use in a bar chart.
A line collects related information (name, value, and category) for use in a line chart.
A bar chart is a data type that supports drawing static bar charts.
A line chart is a data type that supports drawing static line charts.
Chart is an abstract class that holds common properties of the bar and line charts.
AnimatedChart produces animated bar and line charts using BarChart and LineChar objects,
respectively.

Getting Information from User
Your program should read both text and XML files in the correct format and then parse them
to extract information.
Your program should read line-by-line the text file in which commas separate fields. In the
text file, records are grouped by time periods.
Your program should also read and parse an XML file. In the XML file, each element
enclosed by the two tags <record> and </record> contains the date, name, country, value,
and category.
You can find file formats in ERUDM.