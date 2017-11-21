$('#modalDelete').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var idTitulo = button.data('id');
	var tipo = button.data('tipo');
	var entidade = button.data('entidade');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + entidade+'/'+ idTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o objeto: <strong>' 
			+ idTitulo + ' - ' + tipo + '</strong>?' );
});