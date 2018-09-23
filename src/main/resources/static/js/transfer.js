
var messageApi = Vue.resource('/api/account/{id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function() {
        return {
            nameAccount: '',
            id: ''
        }
    },
    // watch: {
    //     messageAttr: function(newVal, oldVal) {
    //         this.nameAccount = newVal.nameAccount;
    //         this.id = newVal.id;
    //     }
    // },
    template:
        '<div><div class="row">' +
        '<input type="nameAccount" class="form-control " placeholder="Введите название счета" v-model="nameAccount" />' +'</div>'+
        '<div class="row">' +
        '<input type="button" class="btn btn-primary btn-block mt-1" value="Сохранить" @click="save" />' + '</div></div>'
    ,
    methods: {
        save: function() {
            var message = { nameAccount: this.nameAccount };

            if (this.id) {
                messageApi.update({id: this.id}, message).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.messages, data.id);
                this.messages.splice(index, 1, data);
                this.nameAccount = ''
                this.id = ''
            })
            )
            } else {
                messageApi.save({}, message).then(result =>
                result.json().then(data => {
                    this.messages.push(data);
                this.text = ''
            })
            )
            }
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});