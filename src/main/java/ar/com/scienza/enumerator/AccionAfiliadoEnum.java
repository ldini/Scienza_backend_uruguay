package ar.com.scienza.enumerator;

public enum AccionAfiliadoEnum {

    VINCULAR_AFILIADO("bindAffiliate","Vincular afiliado"),
    COORDINAR_ENTREGA("coordinateDelivery", "Coordinar entrega"),
    CREAR_CONSULTA("createQuery", "Crear consulta"),
    BORRAR_NOTIFICACION("deleteNotification", "Borrar notificación"),
    BORRAR_CONSULTA("deleteQuery", "Borrar consulta"),
    BORRAR_VARIAS_CONSULTAS("deleteQueryBatch", "Borrar varias consultas"),
    EDITAR_PERFIL_AFILIADO("editAffiliateProfile","Editar datos personales"),
    PERFIL_AFILIADO("getAffiliateProfile","Ver datos personales"),
    OBTENER_COORDINACION("getCoordination", "Obtener coordinación"),
    OBTENER_PROPUESTA_COORDINACION("getCoordinationProposal", "Obtener propuesta de coordinación"),
    PREGUNTAS_FRECUENTES("getFrequentQuestionList", "Ver preguntas frecuentes"),
    VER_NOTIFICACION("getNotification", "Ver detalle de notificacion"),
    LISTA_NOTIFICACIONES("getNotificationList", "Ver lista de notificaciones"),
    VER_PEDIDO("getOrder", "Ver detalle de pedido"),
    LISTA_PEDIDOS("getOrderList", "Ver lista de pedidos"),
    OTROS_ESTUDIOS("getOtherStudyList", "Ver otros estudios"),
    OTRA_FARMACIA("getPharmacySearch", "Elegir otra farmacia"),
    RECETAS("getPrescriptionList", "Ver recetas médicas"),
    VER_CONSULTA("getQuery", "Ver detalle de consulta"),
    LISTA_CONSULTAS("getQueryList", "Ver lista de consultas"),
    TERMINOS("getTermsAndConditions", "Ver términos y condiciones"),
    DETALLE_TRAZAS("getTraceDetail", "Ver detalle de trazas"),
    INICIAR_SESION("login", "Iniciar sesión"),
    CERRAR_SESION("logout", "Cerrar sesión"),
    DESVINCULAR_AFILIADO("unbindAffiliate", "Desvincular afiliado"),
    CREAR_ARCHIVO_OTRO_ESTUDIO("createFileOtherStudy", "Crear archivo para otros estudios"),
    CREAR_ARCHIVO_RECETA("createFilePrescription", "Crear archivo para receta"),
    CREAR_IMAGEN_OTRO_ESTUDIO("createImageOtherStudy", "Crear imagen para otro estudio"),
    CREAR_IMAGEN_RECETA("createImagePrescription", "Crear imagen para receta"),
    ERROR("error", "Error Backend");

    private String descripcion;

    private String codigo;


    private AccionAfiliadoEnum(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    };

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}


