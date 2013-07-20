Fixture = Backbone.Model.extend({

	urlRoot : BASE_REST_URL + "fixtures",
	validate : function(attributes) {
		
	},	
	initialize : function() {			
	}
});

var Fixtures = Backbone.Collection.extend({
  url : BASE_REST_URL + "fixtures",
  model: Fixture,
  reset : function() {
	console.log("resetting");
  }
});

var FixtureView = Backbone.View.extend({

	  el: '#main_container',

	//  events: {
	//    "click .icon":          "open",
	//    "click .button.edit":   "openEditDialog",
	//    "click .button.delete": "destroy"
	 // },

	  initialize: function() {	
	    this.listenTo(this.model, 'change reset add remove', this.render);				
	  },

	  render: function() {
	      
		  var source   = $("#entry-template").html();
		  var template = Handlebars.compile(source);		  		 
		  var html = template(this.model.toJSON());		  		  
		  this.$el.html( html );
		 
	  }

	});
	
