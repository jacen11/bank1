
var messageApi = Vue.resource('/bankAccounts2{/id}');

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
        '<message-row v-for="message in messages" :key="message.id" :message="message" editMethod="editMethod" :messages="messages" />' +
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