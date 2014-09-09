var db = new PouchDB('books');

function addBook() {
            var booktitle = window.document.bookform.titlefield.value;
            var bookisbn = window.document.bookform.isbnfield.value;
            var bookauthor = window.document.bookform.authorfield.value;
            var book = {
                _id: bookisbn,
                title: booktitle,
                author: bookauthor
            };
            db.put(book, function callback(error, result) {
                if (!error) {
                    clearFields();
                    document.getElementById("message").innerHTML =
                            "the new book was successfully added";
                }
            });
        }
        function clearFields() {
            window.document.bookform.titlefield.value="";
            window.document.bookform.authorfield.value="";
            window.document.bookform.isbnfield.value="";
        }
        function showBooks() {
            db.allDocs( {include_docs: true, descending: true},
                        function(err, doc) {
                            showTableOfBooks(doc.rows);
                        } );
        }
        function showTableOfBooks(data) {
            var div = document.getElementById("message");
            var str = "<table border='1' aligh='left'><tr><th>isbn</th>"+
                    "<th>title</th><th>author</th></tr>";
            for(var i=0; i<data.length; i++)
            {
                str +=  "<tr><td>"+data[i].doc._id+
                        "</td><td>"+data[i].doc.title+
                        "</td><td>"+data[i].doc.author+"</td></tr>"
            }
            str += "</table>";
            div.innerHTML = str;
        }
function createdb(){
	var db = new PouchDB('todos');
	var opts = {continuous:true};
	db.replicate.to('http://localhost/couchdb/toto',opts);
	db.replicate.from('http://localhost/couchdb/toto',opts);
}

function addIndicateur(){
	var indicTheme = window.document.indicform.titlefield.value;
}
