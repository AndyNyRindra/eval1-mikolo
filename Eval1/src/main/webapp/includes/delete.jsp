<div class="modal fade" tabindex="-1" id="delete-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Supprimer</h3>

                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                    <i class="fa-solid fa-xmark fs-1"></i>
                </div>
                <!--end::Close-->
            </div>

            <div class="modal-body">
                <p>Etes-vous sur de vouloir supprimer <span id="element-type-name"></span> <span id="element-name"></span> de Id <span id="element-id"></span> ?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Annuler</button>
                <a class="btn btn-danger" id="element-url">Supprimer</a>
            </div>
        </div>
    </div>
</div>