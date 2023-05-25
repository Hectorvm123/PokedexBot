from Types import Types
import json
import numpy
from collections import namedtuple
class Pokemon():
    def __init__(self, id, name,types, hp, attack, defense, specialAttack, specialDefense, speed,
                 species, height, weight, sprite, hires, thumbnail, region):
        self.id = id
        self.name = name
        #self.description = description
        self.types = types
        self.hp= hp
        self.attack= attack
        self.defense= defense
        self.specialAttack= specialAttack
        self.specialDefense= specialDefense
        self.speed= speed
        self.species= species
        self.height= height
        self.weight= weight
        self.sprite = sprite
        self.hires = hires
        self.thumbnail = thumbnail
        self.region = region


    def customPokemonDecoder(pokemonDict):
        return namedtuple('X', pokemonDict.keys())(*pokemonDict.values())