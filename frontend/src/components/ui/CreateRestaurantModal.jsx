import { useState } from "react"
import { useCreateNewRestaurantModal } from "../../store/store"
import { api } from "../../api/axios"

export const CreateRestaurantModal = () => {

  const { createNewRestaurantModalShow, setCreateNewRestaurantModalShow } = useCreateNewRestaurantModal()
  const [ createRestaurant, setCreateRestaurant ] = useState({
    name:"",
    cuisineType:""
  })

  const handleCreateRestaurantForm = (e) => {
    const { name, value } = e.target
    setCreateRestaurant((prev)=>({...prev, [name]:value}))
  }

  const handleCreateRestaurantSubmission =  async(e) => {
    e.preventDefault()
    try {
      const response = await api.post('/restaurants',createRestaurant)
      alert(response.data.message)
    } catch (error) {
      console.error(error)
    }
  }
  
  if(createNewRestaurantModalShow){
    return(
      <>
        <div className="border m-2">
          <div className="flex gap-2">
            <h1>Create New Restaurant</h1>
            <button className="border p-1.5 bg-rose-700 text-white" onClick={()=>setCreateNewRestaurantModalShow(false)}>X</button>
          </div>
          <form action="" onChange={handleCreateRestaurantForm}>
            <div>
              <p>Enter Restaurant Name</p>
              <input type="text" className="border" placeholder="Enter Restaurant Name" name="name" />
            </div>
            <div>
              <p>Enter Cuisine Type</p>
              <input type="text" className="border" placeholder="Enter Cuisine Type" name="cuisineType" />
            </div>
            <button className="border bg-sky-700 text-white p-2" onClick={handleCreateRestaurantSubmission}>Create</button>
            <p>{createRestaurant.name}</p>
            <p>{createRestaurant.cuisineType}</p>
          </form>
        </div>
      </>
    )
  }
}