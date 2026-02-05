package com.hyspain.security;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * Lógica principal para la puerta de seguridad de HySpain.
 * Implementa vinculación por UUID, persistencia en memoria y validación de PIN.
 */
public class DoorMod {

    // Almacena: Coordenadas del bloque -> Datos de la puerta
    private final Map<String, DoorData> activeDoors = new HashMap<>();

    /**
     * Clase interna para representar los datos de cada puerta.
     * Encapsula la identidad del propietario y el código de acceso.
     */
    public static class DoorData {
        public UUID ownerId;
        public String pinCode;

        public DoorData(UUID ownerId, String pinCode) {
            this.ownerId = ownerId;
            this.pinCode = pinCode;
        }
    }

    /**
     * Se ejecuta cuando un jugador intenta interactuar con la puerta.
     * Gestiona la vinculación automática y la verificación de identidad.
     */
    public void onPlayerInteract(String playerUuid, String blockPos) {
        UUID visitorId = UUID.fromString(playerUuid);
        
        // 1. Si la puerta no está registrada, el primero que la toca es el dueño
        if (!activeDoors.containsKey(blockPos)) {
            activeDoors.put(blockPos, new DoorData(visitorId, "1234"));
            System.out.println("Puerta vinculada al nuevo dueño: " + playerUuid);
            return;
        }

        DoorData data = activeDoors.get(blockPos);

        // 2. Verificación de identidad (Vinculación por UUID permanente)
        if (data.ownerId.equals(visitorId)) {
            System.out.println("Dueño reconocido. Abriendo puerta...");
            openDoorAnimation(blockPos);
        } else {
            // 3. Si no es el dueño, se deniega y se solicita el desafío de PIN
            System.out.println("Acceso denegado. Se requiere PIN para la puerta en: " + blockPos);
            promptForPin(playerUuid);
        }
    }

    /**
     * Actualiza el PIN de una puerta con validación defensiva.
     * Utiliza expresiones regulares para garantizar la integridad del formato.
     * * @param blockPos Coordenadas de la puerta.
     * @param newPin El nuevo código propuesto (debe ser de 4 dígitos).
     * @return true si el cambio es válido y se aplica; false en caso contrario.
     */
    public boolean setPin(String blockPos, String newPin) {
        // Validación Senior: Solo permite exactamente 4 caracteres numéricos (0-9)
        if (newPin != null && newPin.matches("\\d{4}")) {
            if (activeDoors.containsKey(blockPos)) {
                activeDoors.get(blockPos).pinCode = newPin;
                System.out.println("PIN actualizado correctamente en: " + blockPos);
                return true;
            }
        }
        
        System.out.println("Error: Formato de PIN inválido. Se requieren 4 dígitos numéricos.");
        return false;
    }

    private void openDoorAnimation(String pos) { 
        /* En el SDK de Hytale, aquí se llamaría a la animación del modelo .model */ 
    }

    private void promptForPin(String playerUuid) { 
        /* Aquí se invocaría la interfaz de usuario definida en el JSON de recursos */ 
    }
}