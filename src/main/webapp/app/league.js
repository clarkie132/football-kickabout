League = Backbone.Model.extend({

	urlRoot : BASE_REST_URL + "leagues",
	validate : function(attributes) {
		
	},	
	initialize : function() {			
	}
});

var Leagues = Backbone.Collection.extend({
  url : BASE_REST_URL + "leagues",
  model: League  
});

var LeagueView = Backbone.View.extend({

	  el: '#main_container',

		events: {
	     "click #save-league":          "save"
	//    "click .button.edit":   "openEditDialog",
	//    "click .button.delete": "destroy"
	 },

	  initialize: function() {	
	    this.listenTo(this.model, 'change reset add remove', this.render);	
		this.render();
	  },

	  render: function() {
	      
		  var source   = $("#league-template").html();
		  var template = Handlebars.compile(source);		  		 
		  var html = template(this.model.toJSON());		  		  
		  this.$el.html( html );
		 
	  },
	  
	  save : function(event) {
			event.preventDefault();
			var league = new League();						
			league.set("name", $("#name").val());
			league.set("division", $("#division").val());
			$('#myModal').modal('hide')
			league.save();			
			this.model.add(league);		
	  }

	});
	
