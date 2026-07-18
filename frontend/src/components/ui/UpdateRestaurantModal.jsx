import { useState } from "react"
import { useUpdateRestaurantModal } from "../../store/store"
import { api } from "../../api/axios"
import { useNavigate } from "react-router-dom"

export const UpdateRestaurantModal = (props) => {

  const restaurantDetails = props.value
  console.log(restaurantDetails);

  const [ updateRestaurant, setUpdateRestaurant ] = useState({
    name:restaurantDetails.name,
    cuisineType:restaurantDetails.cuisineType
  })
  const { updateRestaurantModalShow, setUpdateRestaurantModalShow } = useUpdateRestaurantModal()
  
  const navigate = useNavigate()

  const handleUpdateRestaurantForm = (e) => {
    const { name, value } = e.target
    setUpdateRestaurant((prev)=>({...prev, [name]:value}))
  }

  const handleRestaurantUpdateFormSubmission = async(e) => {
    e.preventDefault()
    try {
      const response = await api.put(`/restaurants/${restaurantDetails.restaurantId}`,updateRestaurant)
      alert(response.data)
      navigate('/admin')
    } catch (error) {
      
    }
  }
  
  if(updateRestaurantModalShow){
    return(
      <>
        <div className="border m-2">
            <div className="flex gap-2">
              <h1>Update Restaurant</h1>
              <button className="border p-1.5 bg-rose-700 text-white" onClick={()=>setUpdateRestaurantModalShow(false)}>X</button>
            </div>
            <form action="" onChange={handleUpdateRestaurantForm}>
              <div>
                <p>Edit Restaurant Name</p>
                <input type="text" className="border" placeholder="Enter Restaurant Name" name="name" value={updateRestaurant.name} />
              </div>
              <div>
                <p>Edit Cuisine Type</p>
                <input type="text" className="border" placeholder="Enter Cuisine Type" name="cuisineType" value={updateRestaurant.cuisineType} />
              </div>
              <button className="border bg-sky-700 text-white p-2" onClick={handleRestaurantUpdateFormSubmission}>Update</button>
              {/* <p>{restaurantDetails.restaurantId}</p> */}
              <p>{updateRestaurant.name}</p>
              <p>{updateRestaurant.cuisineType}</p>
            </form>
          </div>
      </>
    )
  }
}