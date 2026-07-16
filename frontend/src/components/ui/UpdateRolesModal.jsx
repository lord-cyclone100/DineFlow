import { useState } from "react"
import { rolesList } from "../../api/list"
import { useChangeUserRolesToggle } from "../../store/store"
import { api } from "../../api/axios"

export const UpdateRolesModal = (props) => {
  const { changeUserRolesModalShow, setChangeUserRolesModalShow } = useChangeUserRolesToggle()
  const listOfRoles = props.value1
  const userId = props.value2
  const [ updateListOfRoles, setUpdateListOfRoles ] = useState(listOfRoles)

  const handleUpdateListOfRoles = (e) => {
    const { value } = e.target
    setUpdateListOfRoles((prev)=> prev.includes(value) ? prev.filter((role) => role !== value) : [...prev, value])
  }

  const handleRolesSubmit = async() => {
    const response = await api.patch(`/admin/users/${userId}/roles`,{
      roles:updateListOfRoles
    })

    console.log(response.data);
    alert(response.data)
  }

  if(changeUserRolesModalShow){
    return(
      <>
        <div className="border">
          <div className="flex items-center gap-10">
            <h1>Update Roles</h1>
            <button className="border p-1.5 bg-rose-700 text-white" onClick={() => setChangeUserRolesModalShow(false)}>X</button>
          </div>
          {
            rolesList.map((role)=>(
              <div className="flex items-center">
                <input type="checkbox" name={role} value={role} id="" checked={updateListOfRoles.includes(role)} onChange={handleUpdateListOfRoles} />
                <p>{role}</p>
              </div>
            ))
          }
          <button className="p-1.5 bg-teal-600 text-white" onClick={handleRolesSubmit}>
            Update
          </button>
          {updateListOfRoles}
          {userId}
        </div>
      </>
    )
  }
}