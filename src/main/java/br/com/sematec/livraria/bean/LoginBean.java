package br.com.sematec.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sematec.livraria.dao.UsuarioDAO;
import br.com.sematec.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	private Usuario usuario = new Usuario();

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usu�rio " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = UsuarioDAO.getInstance().existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
