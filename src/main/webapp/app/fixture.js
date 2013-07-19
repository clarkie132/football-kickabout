Fixture = Backbone.Model.extend({

	validate : function(attributes) {
		alert("Validating");
	},	
	initialize : function() {
		alert("Welcome to this world");
	}
});


var FixtureView = Backbone.View.extend({

	  el: "main",

	//  events: {
	//    "click .icon":          "open",
	//    "click .button.edit":   "openEditDialog",
	//    "click .button.delete": "destroy"
	 // },

	  initialize: function() {
	 //   this.listenTo(this.model, "change", this.render);
	    this.render();
	  },

	  render: function() {
		  alert("rendering");
		  var source   = $("#entry-template").html();
		  var template = Handlebars.compile(source);
		  this.$el.html( template );
	  }

	});