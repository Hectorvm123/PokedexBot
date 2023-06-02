from types import SimpleNamespace
import discord
from discord.ext import commands
import datetime
from asyncio import sleep
import urllib3
import json
import os
import glob
import random


TOKEN = 'MTA3Nz2NzY0NTgfefwfefwfFst0oDxMDgIY'

intents = discord.Intents.all()

client = commands.Bot(command_prefix='.', intents=intents)

@client.event
async def on_ready():
    await client.change_presence(activity=discord.Activity(type=discord.ActivityType.listening, name=" .info to show commands"))

    print('bot ready')

@client.event
async def on_message(message):
    await client.process_commands(message)
    print(message.author, message.content, message.channel.id)

@client.command()
async def pokemon(ctx, numero):
    channel = client.get_channel(ctx.channel.id)

    http = urllib3.PoolManager()

    url = f'http://localhost:2310/hector/pokemon/{numero}'
    response = http.request("GET", url)

    pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))
    await pokemonEmbed(ctx, pokemon, True)

@client.command()
async def pokemonName(ctx, name):
    channel = client.get_channel(ctx.channel.id)

    http = urllib3.PoolManager()

    url = f'http://localhost:2310/hector/pokemon/name/{name}'
    response = http.request("GET", url)
    pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))
    embed = await pokemonEmbed(ctx, pokemon, False) 
    await ctx.send(embed=embed)   


regiones = ["Kanto", "Jotho", "Hoenn", "Sinnoh","Teselia", "Kalos", "Galar"]

@client.command()
async def info(ctx):
    des = """
  Comandos de TestBot\n
  > Prefix:  .\n

  > pokemon <number>: Searches the Pokemon related to the number\n

  > pokemonName <name>: Searches a Pokemon by name\n

  > pokeRegion <regionName>: Searches all the Pokemons from said region\n

  > register: Saves your username so you can follow your pokedex status\n

  > catch <number>: Registers the Pokemon related to the number wrote\n

  > caughtCount: Tells you your current Pokedex status\n

  > caughtRegion: Same as above but from an specific region\n

  > effectiveness <typeName> <objectivePokemonName>: Tells you how effective is a Type against a Pokemon\n
  
  > typeChart: Shows a image with every single type effectiveness

  > play: Lets you play the "who's that Pokemon?" game from the anime show

  """
    embed = discord.Embed(title="Pokedex",url="https://cdn.discordapp.com/avatars/809827305295314967/babea11271bbf5a89d5bf15220e7c278.webp?size=1024",description= des,
    timestamp=datetime.datetime.utcnow(),
    color=discord.Color.blue())
    embed.set_footer(text="Executed by: {}".format(ctx.author.name))
    embed.set_author(name="Hector Valls Mira",       
    icon_url="https://images8.alphacoders.com/118/thumb-1920-1182414.png")


    await ctx.send(embed=embed)


async def pokemonEmbed(ctx,pokemon,send):   
    des = pokemon.description
    embed = discord.Embed(title=pokemon.name,url=pokemon.hires,description= des,
    timestamp=datetime.datetime.now(),
    color=discord.Color.blue())
    if (len(pokemon.types) == 2):
        embed.add_field(name = "Types:", value = pokemon.types[0].name + " - " + pokemon.types[1].name, inline = False)
    else:
        embed.add_field(name = "Types:", value = pokemon.types[0].name, inline = False)
    
    embed.add_field(name ="Region: ", value = pokemon.region.name, inline = False)


    embed.add_field(name = "HP: ", value = pokemon.hp)
    embed.add_field(name = "Speed: ", value = pokemon.speed)
    embed.add_field(name = "Attack: ", value = pokemon.attack)
    embed.add_field(name = "Defense: ", value = pokemon.defense)
    embed.add_field(name = "Special Attack: ", value = pokemon.specialDefense)
    embed.add_field(name = "Special Defense: ", value = pokemon.specialDefense)
    
    embed.set_footer(text="By: {}".format(ctx.author.name))
    embed.set_author(name=pokemon.id, icon_url=pokemon.hires)
    embed.set_thumbnail(url = pokemon.thumbnail)

    if send:
        await ctx.send(embed=embed)
    else:
        return embed


@client.command()
async def pokeRegion(ctx, region):

    http = urllib3.PoolManager()

    url = f'http://localhost:2310/hector/pokemon/region/{region}'
    response = http.request("GET", url)
    pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))
    
    print(type(pokemon) is list)

    message = await ctx.send(embed= await pokemonEmbed(ctx, pokemon[0], False))
    await message.add_reaction('⏮')
    await message.add_reaction('◀')
    await message.add_reaction('▶')
    await message.add_reaction('⏭')

    def check(reaction, user):
        if user == ctx.author:
            return True
        else:
            ctx.send("Only original user can interact with this messages")
            return False

    i = 0
    reaction = None

    while True:
        if str(reaction) == '⏮':
            i = 0
            await message.edit(embed = await pokemonEmbed(ctx, pokemon[i], False))
        elif str(reaction) == '◀':
            if i > 0:
                i -= 1
                await message.edit(embed = await pokemonEmbed(ctx, pokemon[i], False))
        elif str(reaction) == '▶':
            if i < len(pokemon):
                i += 1
                await message.edit(embed = await pokemonEmbed(ctx, pokemon[i], False))
        elif str(reaction) == '⏭':
            i = len(pokemon)-1
            await message.edit(embed = await pokemonEmbed(ctx, pokemon[i], False))
        
        try:
            reaction, user = await client.wait_for('reaction_add', timeout = 15.0, check = check)
            await message.remove_reaction(reaction, user)
        except:
            break

    await message.clear_reactions()


@client.command()
async def register(ctx):
    channel = client.get_channel(ctx.channel.id)

    http = urllib3.PoolManager()

    userData = str(ctx.author).split('#')
    user = '{{ "name": "{}"}}'.format(userData[0])
    print(user)

    url = f'http://localhost:2310/hector/users'
    response = http.urlopen(method='POST', url=url, body=user , headers={'Content-Type': 'application/json'})
    #embed = await pokemonEmbed(ctx, pokemon, False) 
    if response.status != 201:
        await ctx.send("User alredy exists") 
    else:
        await ctx.send("Register successful")


@client.command()
async def catch(ctx, pokeCaught):
    http = urllib3.PoolManager()

    userData = str(ctx.author).split('#')
    url = 'http://localhost:2310/hector/users/name/{}'.format(userData[0])
    response = http.request("GET", url)
    if response.status == 404:
        await ctx.send("Error, you are not registered") 
    else:
        userComplete = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))

        userData = str(ctx.author).split('#')
        poke = '{{"id": {}}}'.format(pokeCaught)
        user = '{{"id":{}, "name": "{}", "pokemonDTO":[{}] }}'.format(userComplete.id, userData[0], poke)
        print(user)
        url = f'http://localhost:2310/hector/users'
        response = http.urlopen(method='PUT', url=url, body=user , headers={'Content-Type': 'application/json'})
        #embed = await pokemonEmbed(ctx, pokemon, False) 
        if response.status != 204:
            await ctx.send("Either you alredy caught that pokemon, or you wrote it wrong, try again") 
        else:
            await ctx.send("Caught successfully")    
    

@client.command()
async def caughtCount(ctx):
    http = urllib3.PoolManager()

    userData = str(ctx.author).split('#')
    url = 'http://localhost:2310/hector/users/name/{}'.format(userData[0])
    response = http.request("GET", url)
    userComplete = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))

    print(len(userComplete.pokemonDTO))
    if response.status != 200:
        await ctx.send("Error, check you are registered and that the command is correct") 
    else:
        url = f'http://localhost:2310/hector/pokemon'
        response = http.request("GET", url)
        pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))

        if len(userComplete.pokemonDTO) != len(pokemon):
            await ctx.send("You caught {} out of the {} different Pokemon species. Keep up the good work trainer!".format(
                len(userComplete.pokemonDTO), len(pokemon)))  
        else:
             await ctx.send("You caught every single Pokemon out there, you are awesome")


@client.command()
async def caughtRegion(ctx, region):
    http = urllib3.PoolManager()

    userData = str(ctx.author).split('#')
    url = 'http://localhost:2310/hector/users/name/{}'.format(userData[0])
    response = http.request("GET", url)
    userComplete = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))

    print(len(userComplete.pokemonDTO))
    if response.status != 200:
        await ctx.send("Error, check you are registered and that the command is correct") 
    else:
        url = f'http://localhost:2310/hector/pokemon/region/{region}'
        response = http.request("GET", url)
        pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))

        temp = []
        for poke in userComplete.pokemonDTO:
            if poke.region.name == region:
                temp.append(poke)

        if len(temp) != len(pokemon):
            await ctx.send("You caught {} out of the {} different Pokemon species on {}. Keep up the good work trainer!".format(
                len(temp), len(pokemon), region))  
        else:
             await ctx.send("You caught every single Pokemon of {}, you are awesome".format(region))
    


@client.command()
async def effectiveness(ctx, typeU, pokemon):
    http = urllib3.PoolManager()

    url = f'http://localhost:2310/hector/pokemon/name/{pokemon}'
    response = http.request("GET", url)
    pokemon = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))
    
    types = []
    for pokeType in pokemon.types:
        types.append(pokeType.name)


    url = f'http://localhost:2310/hector/effects/attack/{typeU}'
    response = http.request("GET", url)
    effects = json.loads(response.data.decode("utf-8"), object_hook=lambda d: SimpleNamespace(**d))
    print(len(effects))
    print(len(types))

    finalEffect = 1
    for effectType in types:
        print(effectType)
        for effectiveness in effects:
            if effectiveness.defensiveType.name == effectType:
                print(effectiveness.defensiveType.name)
                finalEffect = finalEffect*effectiveness.effect
                print(finalEffect)
    

    if response.status != 200:
        await ctx.send("Error, check you are registered and that the command is correct") 
    else:
        await ctx.send("The {} type has a x{} effectiveness against {}".format(typeU, finalEffect, pokemon.name))


@client.command()
async def typeChart(ctx):
    await ctx.send("https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Pokemon_Type_Chart.svg/800px-Pokemon_Type_Chart.svg.png")



def getImages():
    folder_path = './img'

    file_list = glob.glob(os.path.join(folder_path, '*'))

    file_array = file_list

    return file_array

@client.command()
async def play(ctx):
    files = getImages()
    file =random.choice(files)

    
    with open(file, 'rb') as f:
        picture = discord.File(f)
        await ctx.send(file=picture)

        msg = await client.wait_for('message')

        file_name = os.path.splitext(os.path.basename(file))[0]

        if msg.content == file_name:
            await ctx.send("It's correct. You are awesome ma boy")
        else:
            await ctx.send("If I had added your shadow the answer would be Donkey")

client.run(TOKEN)
