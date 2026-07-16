import { NavLink } from "react-router-dom"

export const AdminPanelUserCard = (props) => {
  // console.log(props.value);
  const {name, email, phoneNumber, status, userId} = props.value
  return(
    <>
      <div className="p-2 border m-4">
        {/* <p>{userId}</p> */}
        <p>{name}</p>
        <p>{email}</p>
        <p>{phoneNumber}</p>
        <p>{status}</p>
        <div className="flex gap-2">
          
          <NavLink to={`/admin/users/${userId}`}>
            <button className="p-1.5 bg-teal-600 text-white">
              View User Details
            </button>
          </NavLink>
        </div>
      </div>
    </>
  )
}