function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var messageApi = Vue.resource('/bankAccounts2{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function() {
        return {
            nameAccount: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.nameAccount = newVal.nameAccount;
            this.id = newVal.id;
        }
    },
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

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div  class="form-group row">' +
                '<div class="col">' +
                    '<label class="col-sm-9 col-form-label">{{ message.nameAccount }}</label>' +
                '</div>' +
                // '<span style="position: absolute; right: 0">' +
                    '<div class="col-sm-2">' +
                        '<input type="button" class="btn btn-primary mt-1" value="Edit" @click="edit" />' +
                    '</div>' +
                    '<div class="col-sm-1">' +
                        '<input type="button" class="btn btn-primary mt-1" value="X" @click="del" />' +
                    '</div>' +
            // '   </span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.message);
        },
        del: function() {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                this.messages.splice(this.messages.indexOf(this.message), 1)
            }
        })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
        ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    created: function() {
        messageApi.get().then(result =>
        result.json().then(data =>
        data.forEach(message => this.messages.push(message))
    )
    )
    },
    methods: {
        editMethod: function(message) {
            this.message = message;
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