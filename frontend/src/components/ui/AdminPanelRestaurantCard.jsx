import { NavLink } from "react-router-dom"

export const AdminPanelRestaurantCard = (props) => {
  const { restaurantId, name, cuisineType } = props.value

  return(
    <>
      <div className="border p-2 m-2">
        <p>{restaurantId}</p>
        <p>{name}</p>
        <p>{cuisineType}</p>
        <NavLink to={`/restaurants/${restaurantId}`}>
          <button className="border bg-sky-700 text-white p-1">View Details</button>
        </NavLink>
      </div>
    </>
  )
}