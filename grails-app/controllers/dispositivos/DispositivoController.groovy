package dispositivos

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DispositivoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dispositivo.list(params), model:[dispositivoCount: Dispositivo.count()]
    }

    def show(Dispositivo dispositivo) {
        respond dispositivo
    }

    def create() {
        respond new Dispositivo(params)
    }

    @Transactional
    def save(Dispositivo dispositivo) {
        if (dispositivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dispositivo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dispositivo.errors, view:'create'
            return
        }

        dispositivo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), dispositivo.id])
                redirect dispositivo
            }
            '*' { respond dispositivo, [status: CREATED] }
        }
    }

    def edit(Dispositivo dispositivo) {
        respond dispositivo
    }

    @Transactional
    def update(Dispositivo dispositivo) {
        if (dispositivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dispositivo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dispositivo.errors, view:'edit'
            return
        }

        dispositivo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), dispositivo.id])
                redirect dispositivo
            }
            '*'{ respond dispositivo, [status: OK] }
        }
    }

    @Transactional
    def delete(Dispositivo dispositivo) {

        if (dispositivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dispositivo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), dispositivo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
