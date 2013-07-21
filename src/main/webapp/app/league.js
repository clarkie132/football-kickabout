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
			var league = new League();						
			league.set("name", $("#name").val());
			league.set("division", $("#division").val());
			$('#myModal').modal('hide');
			var leagues = this.model;
			
			league.save(null, {
					success: function (model, response) {
						console.log("success");
						this.model.add(league);								
					},
					error: function (model, response) {
						// A 201 is not an error but there is no content so 
						// backbone treats it like that
						if(response.status == 201) {
							console.log("success");
							leagues.add(league);								
						} else {
							console.log("error" + response.status);
						}
						
					}
				});
			
	  }

	});
	
