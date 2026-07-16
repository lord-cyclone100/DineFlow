import { useEffect, useState } from "react";
import { useLoaderData, useNavigate, useParams } from "react-router-dom"
import { api } from "../../api/axios";
import { UpdateRolesModal } from "./UpdateRolesModal";
import { useChangeUserRolesToggle } from "../../store/store";

export const ParticularUserSection = (props) => {
  // const user = useLoaderData()
  const params = useParams()
  const navigate = useNavigate()
  const { changeUserRolesModalShow, setChangeUserRolesModalShow } = useChangeUserRolesToggle()

  const [fetchedUser, setFetchedUser] = useState(null)

  useEffect(() => {
    const fetchParticularUserDetails = async () => {
      try {
        const response = await api.get(`/admin/users/${params.userId}`)
        setFetchedUser(response.data)
      } catch (error) {

      }
    }
    fetchParticularUserDetails()
  }, [])

  const handleStatusChange = async () => {
    if (!fetchedUser) return;
    try {
      const response = await api.patch(`/admin/users/${fetchedUser.userId}/status`, {}, {
        params: {
          userStatus: fetchedUser.status === 'ACTIVE' ? 'SUSPENDED' : 'ACTIVE'
        }
      })
      alert(response.data)
      setFetchedUser(prev => ({
        ...prev,
        status: prev.status === 'ACTIVE' ? 'SUSPENDED' : 'ACTIVE'
      }))
    } catch (error) {
      console.log(error);
    }
  }

  const handleUserDelete = async () => {
    if (!fetchedUser) return;
    const response = await api.delete(`/admin/users/${fetchedUser.userId}`)
    alert(response.data)
    navigate('/admin')
  }

  if (!fetchedUser) {
    return <div>Loading user details...</div>;
  }
  return (
    <>
      Hello User
      <p>{fetchedUser.userId}</p>
      <p>{fetchedUser.name}</p>
      <p>{fetchedUser.email}</p>
      <p>{fetchedUser.phoneNumber}</p>
      <p>{fetchedUser.role}</p>
      <p>{fetchedUser.status}</p>
      <div className="flex gap-4">
        <button className="p-1.5 bg-teal-600 text-white" onClick={handleStatusChange}>
          {fetchedUser.status === 'ACTIVE' ? 'SUSPEND' : 'ACTIVE'}
        </button>
        <button className="p-1.5 bg-teal-600 text-white" onClick={() => setChangeUserRolesModalShow(true)}>
          Update Roles
        </button>
        <button className="p-1.5 bg-rose-600 text-white" onClick={handleUserDelete}>
          Delete User
        </button>
      </div>
      <UpdateRolesModal value1={fetchedUser.role} value2={fetchedUser.userId} />
    </>
  )
}