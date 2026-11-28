import os
import requests

BASE_URL = os.getenv("API_URL", "http://localhost:8080/api/tasks")


def get_tasks():
    try:
        response = requests.get(BASE_URL, timeout=60)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.Timeout:
        print("[ERROR] Tiempo de espera agotado (el servidor está despertando o no responde).")
    except requests.exceptions.ConnectionError:
        print(f"[ERROR] No se pudo conectar con {BASE_URL}. ¿Está encendido el servidor?")
    except requests.exceptions.HTTPError as e:
        print(f"[ERROR HTTP] {e.response.status_code}: {e.response.text}")
    except requests.exceptions.RequestException as e:
        print(f"[ERROR INESPERADO] {e}")
    return None


def create_task(title: str, completed: bool = False):
    payload = {"title": title, "completed": completed}
    try:
        response = requests.post(BASE_URL, json=payload, timeout=60)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.Timeout:
        print("[ERROR] Tiempo de espera agotado al crear la tarea.")
    except requests.exceptions.ConnectionError:
        print(f"[ERROR] No se pudo conectar con {BASE_URL}.")
    except requests.exceptions.HTTPError as e:
        print(f"[ERROR HTTP] {e.response.status_code}: {e.response.text}")
    except requests.exceptions.RequestException as e:
        print(f"[ERROR INESPERADO] {e}")
    return None


def update_task(task_id: int, title: str, completed: bool):
    payload = {"title": title, "completed": completed}
    try:
        response = requests.put(f"{BASE_URL}/{task_id}", json=payload, timeout=60)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.HTTPError as e:
        print(f"[ERROR HTTP] {e.response.status_code}: {e.response.text}")
    except requests.exceptions.RequestException as e:
        print(f"[ERROR] Falló la actualización: {e}")
    return None


def delete_task(task_id: int):
    try:
        response = requests.delete(f"{BASE_URL}/{task_id}", timeout=60)
        response.raise_for_status()
        return True
    except requests.exceptions.HTTPError as e:
        print(f"[ERROR HTTP] {e.response.status_code}: {e.response.text}")
    except requests.exceptions.RequestException as e:
        print(f"[ERROR] Falló el borrado: {e}")
    return False


if __name__ == "__main__":
    print(f"Conectando a: {BASE_URL}\n")

    print("1. Creando tarea...")
    nueva = create_task("Probar cliente Python contra Spring Boot", False)
    print("Respuesta:", nueva)

    print("\n2. Listando todas las tareas...")
    tareas = get_tasks()
    print("Tareas en DB:", tareas)

    if nueva and "id" in nueva:
        task_id = nueva["id"]
        print(f"\n3. Marcando tarea {task_id} como completada...")
        actualizada = update_task(task_id, nueva["title"], True)
        print("Actualizada:", actualizada)

        print(f"\n4. Eliminando tarea {task_id}...")
        eliminada = delete_task(task_id)
        print("Eliminada:", eliminada)
