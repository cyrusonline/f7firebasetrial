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
      <pre>{{lectures}}</pre>
     
    </f7-block>
  </f7-page>
</template>
<script>
export default {
  name: 'Home',
  data() {
    return {
      title: 'Hello World',
      lectures:''
    };
  },
  async created(){
    var options = {
    "datePrefix": '__DATE:',
    "fieldValueDelete": "__DELETE",
    "fieldValueServerTimestamp" : "__SERVERTIMESTAMP",
    "persist": true,
    "timestampsInSnapshots" : false,
    "config" : {

    }
  };

 var db = null;

   await Firestore.initialise(options).then(function(database) {
     db = database

    console.log('alert')
    console.log(database)
  })
  
  // var a = db.collection('lectures')
  var a =   db.collection('lectures').orderBy('name').get().then
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
  
  console.log(a)
 var b= window.Firestore.collection('lectures')
 console.log(b)


  // const firestore =  Firestore.initialise(options);
  



  }
};
</script>
