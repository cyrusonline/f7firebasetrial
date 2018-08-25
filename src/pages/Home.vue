<template>
  <f7-page>
    <f7-navbar sliding>
      <f7-nav-left>
        <f7-link icon-f7="icon-bars" panel-open="left"></f7-link>
      </f7-nav-left>
      <f7-nav-title>
        Home
      </f7-nav-title>
    </f7-navbar>
    <f7-block-title>{{ title }}</f7-block-title>
    <f7-block inner>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
      Distinctio est aliquam officiis quaerat placeat, cum explicabo magni
      soluta totam maxime autem minima accusamus eos suscipit dignissimos
      corporis modi voluptatum fugiat!
    </f7-block>
  </f7-page>
</template>
<script>
export default {
  name: 'Home',
  data() {
    return {
      title: 'Hello World'
    };
  },
  created(){
    var options = {
    "datePrefix": '__DATE:',
    "fieldValueDelete": "__DELETE",
    "fieldValueServerTimestamp" : "__SERVERTIMESTAMP",
    "persist": true,
    "timestampsInSnapshots" : false,
    "config" : {

    }
  };

 

  var db = Firestore.initialise(options).then(function(database) {
    // myDatabaseReference = database;
    console.log('alert')
    console.log(database)
  });

 db.collection('lectures').orderBy('name').get().then
        (querySnapshot =>{
            querySnapshot.forEach(doc=>{
               
                const data = {
                    
                    'id':doc.id,
                    'name':doc.data().name,
                    'lessons':doc.data().lessons,
               
                }
                this.lectures.push(data)
                console.log(data)
            })

        })

  }
};
</script>
