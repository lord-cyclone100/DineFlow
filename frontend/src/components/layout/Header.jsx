import { NavLink } from "react-router-dom"

export const Header = () => {
  const getNavLinkColor = ({isActive}) => {
    return{
      color:isActive ? "yellow" : "white",
    };
  }
	return(
		<div className="navbar bg-base-100 shadow-sm">
			<div className="flex-1">
				<a className="btn btn-ghost text-xl">MOVIE</a>
			</div>
			<div className="flex-none">
				<ul className="menu menu-horizontal px-1">
					<li><NavLink to="/" style={getNavLinkColor}>Home</NavLink></li>
					{/* <li><NavLink to="/movie" style={getNavLinkColor}>Movies</NavLink></li> */}
					<li><NavLink to="/contact" style={getNavLinkColor}>Contact</NavLink></li>
					<li><NavLink to="/about" style={getNavLinkColor}>About</NavLink></li>
				</ul>
			</div>
		</div>
	)
}