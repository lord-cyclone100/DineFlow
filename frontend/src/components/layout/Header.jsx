import { NavLink } from "react-router-dom"

export const Header = () => {
  const getNavLinkColor = ({isActive}) => {
    return{
      color:isActive ? "yellow" : "white",
    };
  }
	return(
		<div className="border-2">
			<div className="flex-1">
				<a className="btn btn-ghost text-xl">MOVIE</a>
			</div>
			<div className="flex-none">
				<ul className="flex items-center gap-20 text-rose-500">
					<li><NavLink to="/" className=''>Home</NavLink></li>
					{/* <li><NavLink to="/movie" style={getNavLinkColor}>Movies</NavLink></li> */}
					<li><NavLink to="/contact">Contact</NavLink></li>
					<li><NavLink to="/about" >About</NavLink></li>
					<li><NavLink to="/profile" >Profile</NavLink></li>
				</ul>
			</div>
		</div>
	)
}