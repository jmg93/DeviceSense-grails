package dashboards

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DashboardController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dashboard.list(params), model:[dashboardCount: Dashboard.count()]
    }

    def show(Dashboard dashboard) {
        respond dashboard
    }

    def create() {
        respond new Dashboard(params)
    }

    @Transactional
    def save(Dashboard dashboard) {
        if (dashboard == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dashboard.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dashboard.errors, view:'create'
            return
        }

        dashboard.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dashboard.label', default: 'Dashboard'), dashboard.id])
                redirect dashboard
            }
            '*' { respond dashboard, [status: CREATED] }
        }
    }

    def edit(Dashboard dashboard) {
        respond dashboard
    }

    @Transactional
    def update(Dashboard dashboard) {
        if (dashboard == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dashboard.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dashboard.errors, view:'edit'
            return
        }

        dashboard.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dashboard.label', default: 'Dashboard'), dashboard.id])
                redirect dashboard
            }
            '*'{ respond dashboard, [status: OK] }
        }
    }

    @Transactional
    def delete(Dashboard dashboard) {

        if (dashboard == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dashboard.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dashboard.label', default: 'Dashboard'), dashboard.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dashboard.label', default: 'Dashboard'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
