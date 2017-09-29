package kairosdb

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MetricaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Metrica.list(params), model:[metricaCount: Metrica.count()]
    }

    def show(Metrica metrica) {
        respond metrica
    }

    def create() {
        respond new Metrica(params)
    }

    @Transactional
    def save(Metrica metrica) {
        if (metrica == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (metrica.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond metrica.errors, view:'create'
            return
        }

        metrica.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'metrica.label', default: 'Metrica'), metrica.id])
                redirect metrica
            }
            '*' { respond metrica, [status: CREATED] }
        }
    }

    def edit(Metrica metrica) {
        respond metrica
    }

    @Transactional
    def update(Metrica metrica) {
        if (metrica == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (metrica.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond metrica.errors, view:'edit'
            return
        }

        metrica.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'metrica.label', default: 'Metrica'), metrica.id])
                redirect metrica
            }
            '*'{ respond metrica, [status: OK] }
        }
    }

    @Transactional
    def delete(Metrica metrica) {

        if (metrica == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        metrica.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'metrica.label', default: 'Metrica'), metrica.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'metrica.label', default: 'Metrica'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
